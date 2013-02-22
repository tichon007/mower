package com.mowitnow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beust.jcommander.JCommander;
import com.mowitnow.entity.generated.Mower;
import com.mowitnow.entity.generated.Root;
import com.mowitnow.service.DefaultMowerService;
import com.mowitnow.service.IMowerService;
import com.mowitnow.util.MowerUtil;

/**
 * Main class
 */
public class MowerMain
{
    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(MowerMain.class);

    /**
     * Parameter : -f <absolute_file_path>
     * @param -f args the file path ( xml or txt )
     */
    public static void main(String[] args)
    {
        Root root = null;

        MowerParameters jct = new MowerParameters();
        new JCommander(jct, args);

        // Get para
        if (jct.filePath != null)
        {
            if (jct.filePath.contains(".xml"))
                root = MowerUtil.parseXml(jct.filePath);
            else if (jct.filePath.contains(".txt"))
                root = MowerUtil.parseTextFile(jct.filePath);
            else
                logger.info("File extension not recognized (xml or txt)");
        }
        else
        {
            logger.info("No param provided");
            System.exit(1);
        }

        if (root == null)
        {
            logger.info("The file " + args[0] + " can't be parsed");
            System.exit(1);
        }

        for (Mower mower : root.getMowers())
        {
            // Launch navigation treatment
            IMowerService mowerService = new DefaultMowerService();
            mowerService.mowerNavigation(mower, root.getGrid());
            System.out.println("Mower end position & orientation : " + mower.getPositionX() + " "
                + mower.getPositionY() + " " + mower.getOrientation().value());
        }

    }

}
