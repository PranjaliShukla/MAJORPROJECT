package com.example.majorproject

import android.content.Context
import android.graphics.Bitmap
import org.tensorflow.lite.Interpreter
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class DeepfakeModel(context: Context) {

    private val interpreter: Interpreter
    private val imageSize = 224

    init {
        interpreter = Interpreter(loadModelFile(context))
    }

    private fun loadModelFile(context: Context): MappedByteBuffer {
        val fileDescriptor = context.assets.openFd("deefake_model.tflite")
        val inputStream = fileDescriptor.createInputStream()
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength

        return fileChannel.map(
            FileChannel.MapMode.READ_ONLY,
            startOffset,
            declaredLength
        )
    }

    fun predict(bitmap: Bitmap): Float {

        val byteBuffer =
            ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3)

        byteBuffer.order(ByteOrder.nativeOrder())
        byteBuffer.rewind()

        val pixels = IntArray(imageSize * imageSize)
        bitmap.getPixels(pixels, 0, imageSize, 0, 0, imageSize, imageSize)

        for (pixel in pixels) {
            val r = ((pixel shr 16) and 0xFF)
            val g = ((pixel shr 8) and 0xFF)
            val b = (pixel and 0xFF)

            byteBuffer.putFloat((r / 127.5f) - 1f)
            byteBuffer.putFloat((g / 127.5f) - 1f)
            byteBuffer.putFloat((b / 127.5f) - 1f)
        }

        val output = Array(1) { FloatArray(1) }
        interpreter.run(byteBuffer, output)

        return output[0][0]
    }

    fun close() {
        interpreter.close()
    }
}