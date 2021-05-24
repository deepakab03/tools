package com.deepak.tools.get_a_vac;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.awt.AWTException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class GetItTest {

    @Test
    public void testParsing() throws AWTException {
        String json = "{\"centers\":[{\"center_id\":249068,\"name\":\"Hebbagodi PHC\",\"address\":\"Nathal Bihari Vajapayee Circle\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Anekal\",\"pincode\":560099,\"lat\":12,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"5d1a1977-9f74-4337-b601-47b0aa14afb5\",\"date\":\"24-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"],\"available_capacity_dose1\":0,\"available_capacity_dose2\":0},{\"session_id\":\"25159f34-c253-4dea-a656-5aebf096ce85\",\"date\":\"25-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"],\"available_capacity_dose1\":0,\"available_capacity_dose2\":0},{\"session_id\":\"05b45372-ea09-412a-92c4-5d6e6f46d20b\",\"date\":\"26-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"],\"available_capacity_dose1\":0,\"available_capacity_dose2\":0},{\"session_id\":\"8de3b590-8ec5-469d-afed-0f86ee1c973d\",\"date\":\"27-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"],\"available_capacity_dose1\":0,\"available_capacity_dose2\":0},{\"session_id\":\"48a8c14a-8391-4155-8563-635fc996b3b5\",\"date\":\"28-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"],\"available_capacity_dose1\":0,\"available_capacity_dose2\":0},{\"session_id\":\"2abbaec8-f2e8-49c0-9953-ba09a93d4ea0\",\"date\":\"29-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"],\"available_capacity_dose1\":0,\"available_capacity_dose2\":0}]},{\"center_id\":249049,\"name\":\"Chandapura PHC\",\"address\":\"Hosur Main Road\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Anekal\",\"pincode\":560099,\"lat\":12,\"long\":77,\"from\":\"10:00:00\",\"to\":\"16:00:00\",\"fee_type\":\"Free\",\"sessions\":[{\"session_id\":\"f0b776b1-84c4-4545-a7cb-793e929ca200\",\"date\":\"25-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"],\"available_capacity_dose1\":0,\"available_capacity_dose2\":0},{\"session_id\":\"afb786c4-8ce7-4586-84e2-4a6d32cbd6f3\",\"date\":\"26-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"],\"available_capacity_dose1\":0,\"available_capacity_dose2\":0},{\"session_id\":\"8f91b300-3918-4a0e-8b98-d56dc385ee23\",\"date\":\"27-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"],\"available_capacity_dose1\":0,\"available_capacity_dose2\":0},{\"session_id\":\"75c691c1-c514-4140-9fd7-09e3a2697f12\",\"date\":\"28-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"],\"available_capacity_dose1\":0,\"available_capacity_dose2\":0},{\"session_id\":\"b329e0ae-6ffa-4394-83da-74ae3a7acf18\",\"date\":\"29-05-2021\",\"available_capacity\":0,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"],\"available_capacity_dose1\":0,\"available_capacity_dose2\":0}]},{\"center_id\":582832,\"name\":\"Sparsh Hospital Basement\",\"address\":\"29 P2 The Health City Hosur Rd Bommasandra Industrial Area Bengaluru\",\"state_name\":\"Karnataka\",\"district_name\":\"Bangalore Urban\",\"block_name\":\"Anekal\",\"pincode\":560099,\"lat\":12,\"long\":77,\"from\":\"09:00:00\",\"to\":\"18:00:00\",\"fee_type\":\"Paid\",\"sessions\":[{\"session_id\":\"b7812f51-f541-4105-90c8-eac92ae87bee\",\"date\":\"25-05-2021\",\"available_capacity\":48,\"min_age_limit\":45,\"vaccine\":\"COVISHIELD\",\"slots\":[\"09:00AM-11:00AM\",\"11:00AM-01:00PM\",\"01:00PM-03:00PM\",\"03:00PM-06:00PM\"],\"available_capacity_dose1\":0,\"available_capacity_dose2\":48},{\"session_id\":\"d9e7056b-e740-4f02-9a02-3688b0f6a5bc\",\"date\":\"25-05-2021\",\"available_capacity\":20,\"min_age_limit\":18,\"vaccine\":\"COVISHIELD\",\"slots\":[\"10:00AM-11:00AM\",\"11:00AM-12:00PM\",\"12:00PM-01:00PM\",\"01:00PM-04:00PM\"],\"available_capacity_dose1\":1,\"available_capacity_dose2\":20}],\"vaccine_fees\":[{\"vaccine\":\"COVISHIELD\",\"fee\":\"850\"}]}]}";
            
        boolean result = GetIt.parseResult(18, json);
        
        assertThat(result,  is(true));
    }
    
    @Test
    public void testParsing2() throws AWTException, IOException, URISyntaxException {
        String json = new String (Files.readAllBytes(Paths.get(getClass().getResource("/test_json.json").toURI()))); 
            
        boolean result = GetIt.parseResult(18, json);
        
        assertThat(result,  is(true));
    }
}
