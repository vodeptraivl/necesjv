package necesjv.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {
    private final int BUF_SIZE_1KB = 1024; //1kb
    private long sizeSplit = 10 * 1024;

    public int split(String fileName, InputStream inputStream, String outputPath) throws Exception {
        int fileNum = 0;
        this.createDirectory(outputPath);

        byte[] buf = new byte[BUF_SIZE_1KB];

        BufferedInputStream bis = new BufferedInputStream(inputStream);
        BufferedOutputStream os = null;

        int sizeCnt = 0;
        int index = 1;
        int ret = -1;
        boolean isFirstCreateFlg = true;

        while((ret = bis.read(buf)) > 0) {
            if ((sizeCnt >= this.sizeSplit) || isFirstCreateFlg) {
                this.closeBufferdOutputStream(os);
                os = this.getBufferedOutputStream(
                        this.absolutePath(outputPath, fileName + "_" +  index++)
                );
                isFirstCreateFlg = false;
                sizeCnt = 0;
                fileNum++;
            }
            os.write(buf, 0, ret);
            os.flush();
            sizeCnt++; // kb
        }

        this.closeBufferdOutputStream(os);
        this.closeBufferdInputStream(bis);

        return fileNum;
    }

    public void join(byte[] byteData, BufferedOutputStream outStream) throws Exception {
        outStream.write(byteData);
        outStream.flush();
    }

    // Private Method
    private String absolutePath(String filePath, String fileName) {
        return filePath + File.separator + fileName;
    }

    private void createDirectory(String outputDirectory) {
        File output = new File(outputDirectory);
        if(!output.exists()) {
            output.mkdirs();
        }
    }

    private void closeBufferdOutputStream(BufferedOutputStream os) throws IOException{
        if (os != null) {
            os.flush();
            os.close();
        }
    }

    private void closeBufferdInputStream(BufferedInputStream is) throws IOException {
        if (is != null) {
            is.close();
        }
    }

    private BufferedOutputStream getBufferedOutputStream(String fineName) throws Exception {
        File f = new File(fineName);
        f.createNewFile();
        return new BufferedOutputStream(new FileOutputStream(f));
    }

}
