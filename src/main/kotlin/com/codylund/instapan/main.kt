package com.codylund.instapan

import java.awt.Color
import java.awt.image.BufferedImage
import java.util.logging.Logger
import javax.imageio.ImageIO

private val LOGGER = Logger.getLogger("main")

fun main(args: Array<String>) {
    // Parse args.
    val args = Args.fromInput(args)

    // Read input image.
    val inputImage = ImageIO.read(args.inputFile)

    // Crop left half and save to file.
    val leftImage = inputImage.getSubimage(0, 0, inputImage.width/2, inputImage.height)
    ImageIO.write(leftImage, "jpg", args.outputFileLeft)
    LOGGER.info("Wrote left crop to ${args.outputFileLeft.absolutePath}")

    // Crop right half and save to file.
    val rightImage = inputImage.getSubimage(inputImage.width/2, 0, inputImage.width/2, inputImage.height)
    ImageIO.write(rightImage, "jpg", args.outputFileRight)
    LOGGER.info("Wrote right crop to ${args.outputFileRight.absolutePath}")

    // Create a 4x5 canvas with white background, draw input image in center and save to file.
    val cropImage = getWhite4x5Canvas(inputImage)
    cropImage.createGraphics().drawImage(inputImage, 0, (cropImage.height-inputImage.height)/2, null)
    ImageIO.write(cropImage, "jpg", args.outputFileCrop)
    LOGGER.info("Wrote full crop to ${args.outputFileCrop.absolutePath}")
}

private fun getWhite4x5Canvas(inputImage: BufferedImage): BufferedImage {
    return BufferedImage(
        inputImage.width,
        (inputImage.width*1.25).toInt(),
        inputImage.type
    ).apply {
        for (x in 0 until width) {
            for (y in 0 until height) {
                setRGB(x, y, Color.WHITE.rgb)
            }
        }
    }
}