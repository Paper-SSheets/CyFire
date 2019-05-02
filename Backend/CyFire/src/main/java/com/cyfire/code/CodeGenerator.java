package com.cyfire.code;

/**
 * 
 * Generates a 7-digit code to be used for verification purposes.
 * 
 * Lowkey redundant. Unnecessary to have its own package. Did so for cleanness,
 * but it's only used once, so should be cleaned up.
 * 
 * @author Steven Sheets
 *
 */
public class CodeGenerator
{

	public Integer sevenDigit()
	{
		return (int) ((Math.random() * 9000000) + 1000000);
	}
}
