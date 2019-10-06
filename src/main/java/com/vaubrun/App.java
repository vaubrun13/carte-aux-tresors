package com.vaubrun;

import com.vaubrun.exception.BadApplicationParameter;
import com.vaubrun.exception.MissingParameterException;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.text.MessageFormat;

@Log4j2
public class App {
    public static void main(String[] args) throws MissingParameterException, BadApplicationParameter {
        if (args.length < 2) {
            throw new MissingParameterException("Parameters for input and/or output files are missing, expecting 2 parameters");
        }
        //param 1 = path for input file
        File inputFile = new File(args[0]);
        if (!inputFile.exists()) {
            throw new BadApplicationParameter(MessageFormat.format("Input file does not exists or path is invalid: {0}", inputFile.getAbsolutePath()));
        }
        //param 2 = path for output file
        File outputFile = new File(args[1]);
        if (outputFile.exists()) {
            throw new BadApplicationParameter(MessageFormat.format("Output file already exists: {0}", outputFile.getAbsolutePath()));
        }
        log.info("Running");
    }
}
