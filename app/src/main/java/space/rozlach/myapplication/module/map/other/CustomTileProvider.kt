package space.rozlach.myapplication.module.map.other

import android.content.res.AssetManager
import com.google.android.gms.maps.model.Tile
import com.google.android.gms.maps.model.TileProvider
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream


class CustomMapTileProvider(private val mAssets: AssetManager) : TileProvider {
    override fun getTile(x: Int,  y: Int, zoom: Int): Tile? {
        val y = fixYCoordinate(y, zoom);
        val image = readTileImage(x, y, zoom)
        return if (image == null) null else Tile(TILE_WIDTH, TILE_HEIGHT, image)
    }

    private fun fixYCoordinate(y: Int, zoom: Int): Int {
        val size = 1 shl zoom // size = 2^zoom
        return size - 1 - y
    }

    private fun readTileImage(x: Int, y: Int, zoom: Int): ByteArray? {
        var into: InputStream? = null
        var buffer: ByteArrayOutputStream? = null
        return try {
            into = mAssets.open(getTileFilename(x, y, zoom))
            buffer = ByteArrayOutputStream()
            var nRead: Int
            val data = ByteArray(BUFFER_SIZE)
            while (into.read(data, 0, BUFFER_SIZE).also {
                    nRead = it
                } != -1) {
                buffer.write(data, 0, nRead)
            }
            buffer.flush()
            buffer.toByteArray()
        } catch (e: IOException) {
            e.printStackTrace()
            null
        } catch (e: OutOfMemoryError) {
            e.printStackTrace()
            null
        } finally {
            if (into != null) try {
                into.close()
            } catch (ignored: Exception) {
            }
            if (buffer != null) try {
                buffer.close()
            } catch (ignored: Exception) {
            }
        }
    }

    private fun getTileFilename(x: Int, y: Int, zoom: Int): String {
        return "map/$zoom/$x/$y.png"
    }

    companion object {
        private const val TILE_WIDTH = 256
        private const val TILE_HEIGHT = 256
        private const val BUFFER_SIZE = 16 * 1024
    }
}