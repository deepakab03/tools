package com.deepak.tools.test.spring.session.util;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.session.MapSession;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.events.SessionCreatedEvent;
import org.springframework.session.events.SessionDeletedEvent;

/**
 * Publishes creation and destruction events. The latter need to be triggered by callind the <code>deleteById()</code>
 * explicitly
 *
 * @author abrahd2
 */
public class CustomMapSessionRepo extends MapSessionRepository {
    private static final Logger logger = LoggerFactory.getLogger(CustomMapSessionRepo.class);

    private ApplicationEventPublisher applicationEventPublisher;

    private Map<String, Session> sessions;

    private Timer sessionMonitorTimer;

    public CustomMapSessionRepo(Map<String, Session> sessions, ApplicationEventPublisher applicationEventPublisher) {
        super(sessions);
        this.sessions = sessions;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @PostConstruct
    public void initTimerToKickExpiredSessionsOut() {
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                if (sessions.size() == 0) {
                    return;
                }

                for (Map.Entry<String, Session> entry: sessions.entrySet()) {
                    if (entry.getValue().isExpired()) {
                        logger.info("Kicking out expired session: {}", entry.getKey());
                        deleteById(entry.getKey());
                    }
                }

            }
        };
        sessionMonitorTimer = new Timer(true);
        sessionMonitorTimer.schedule(timerTask, 90 * 1000, 60 * 1000);
    }

    @PreDestroy
    public void destroy() {
        if (sessionMonitorTimer != null) {
            sessionMonitorTimer.cancel();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        destroy();
        super.finalize();
    }

    @Override
    public MapSession createSession() {
        logger.info("SESSION being created");
        MapSession session = super.createSession();
        applicationEventPublisher.publishEvent(new SessionCreatedEvent(this, session) );
        return session;
    }

    @Override
    public void deleteById(String id) {
        logger.info("SESSION being deleted!!    ");
        //DON'T use findById() as that calls this method
        MapSession session = (MapSession) sessions.get(id);
        super.deleteById(id);
        if (session != null) {
            applicationEventPublisher.publishEvent(new SessionDeletedEvent(this, session) );
        }
    }
}
