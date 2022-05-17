package com.codylund.instapan

import java.io.File

/**
 * A very basic container for parsed input arguments.
 */
data class Args constructor(
    val inputFile: File,
    val outputFileLeft: File,
    val outputFileRight: File,
    val outputFileCrop: File
) {
    companion object {
        /**
         * @return an [Args] instance based on the provided input parameters.
         */
        fun fromInput(args: Array<String>): Args {
            // We assume the user passed in two input args.
            val inputFileStr = args[0]
            val outputDirStr = args[1]

            // We assume the input file exists and is a jpg.
            val inputFile = File(inputFileStr)
            // We build the output file names based on the input file names.
            val outFileName = name@{ suffix: String ->
                return@name "${inputFile.nameWithoutExtension}_$suffix.${inputFile.extension}"
            }

            return Args(
                inputFile,
                File(outputDirStr, outFileName("left")),
                File(outputDirStr, outFileName("right")),
                File(outputDirStr, outFileName("crop"))
            )
        }
    }
}
