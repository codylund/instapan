# instapan
A very simple Java utility for cropping 4x3 landscape photos for instagram.

The utility expects a 4x3 jpg as input and outputs the following:
1. A portrait crop of the left half of the input image.
2. A portrait crop of the right half of the input image.
3. The full original image fitted a 4x5 white canvas and centered vertically.

## Usage
```bash
java -jar instapan.jar [input image jpg path] [output image path]
```

WARNING: there is little to no validation of inputs. Always pass in
in 4x3 jpg. Otherwise, you are on your own.