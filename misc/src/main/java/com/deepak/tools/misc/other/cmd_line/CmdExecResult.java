package com.deepak.tools.misc.other.cmd_line;

import java.util.List;

public class CmdExecResult {

	private int exitCode;
	private List<String> output;

	public CmdExecResult(int exitCode, List<String> output) {
		super();
		this.exitCode = exitCode;
		this.output = output;
	}

	public int getExitCode() {
		return exitCode;
	}

	public void setExitCode(int exitCode) {
		this.exitCode = exitCode;
	}

	public List<String> getOutput() {
		return output;
	}

	public void setOutput(List<String> output) {
		this.output = output;
	}
}
