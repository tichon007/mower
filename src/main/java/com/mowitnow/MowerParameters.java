package com.mowitnow;

import com.beust.jcommander.Parameter;

public class MowerParameters
{

    @Parameter(names = {"-f"}, description = "File path")
    public String filePath = "";

}