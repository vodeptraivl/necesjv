package necesjv.file;
import java.io.*;

import org.apache.commons.io.FileUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import java.util.Base64;
import java.net.URLEncoder;

public class NecesFile {
    public void moveFile(String from, String to, String fileName) {
        try {
            File fileFrom = new File(from);
            if(fileFrom.exists()) {
                File ROOT = new File(to);
                if(!ROOT.exists()) {
                    ROOT.mkdirs();
                }
                FileOutputStream out = new FileOutputStream(String.join(File.separator, to,fileName));
                out.write(readFileToByteArray(fileFrom));
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static byte[] readFileToByteArray(File file){
        FileInputStream fis = null;
        byte[] bArray = new byte[(int) file.length()];
        try{
            fis = new FileInputStream(file);
            fis.read(bArray);
            fis.close();

        }catch(Exception ioExp){
            ioExp.printStackTrace();
        }
        return bArray;
    }

    public void saveFile(String Path, String name, Byte data) throws Exception {
        String pathFile = String.join(File.separator,Path,name);
        File outputDirectory = new File(Path);
        if(outputDirectory.exists()) {
            FileUtils.deleteDirectory(outputDirectory);
        }
        File file = new File(pathFile);
        FileOutputStream out = new FileOutputStream(file);
        out.write(data);
        out.flush();
        out.close();
    }

    public void unzip(String zipFilePath, String destDir) throws Exception{
        File dir = new File(destDir);
        if(!dir.exists()) dir.mkdirs();
        FileInputStream fis;
        byte[] buffer = new byte[1024];
        fis = new FileInputStream(zipFilePath);
        ZipInputStream zis = new ZipInputStream(fis);
        ZipEntry ze = zis.getNextEntry();
        while(ze != null){
            String fileName = ze.getName();
            File newFile = new File(destDir + File.separator + fileName);
            new File(newFile.getParent()).mkdirs();
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            zis.closeEntry();
            ze = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
        fis.close();
        File file = new File(zipFilePath);
        file.delete();
    }

    private boolean zipFile(String pathFileTemp, HashMap<String, String> filePaths) throws Exception {
        boolean res = false;
        File zf = new File(pathFileTemp);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(zf));
        ZipOutputStream zos = new ZipOutputStream(bos);
        for(Map.Entry<String, String> aFile : filePaths.entrySet()) {
            File tmpDir = new File(aFile.getKey());
            if(tmpDir.exists()) {
                res = true;
                zos.putNextEntry(new ZipEntry(aFile.getValue()));
                InputStream inputStream = new BufferedInputStream(new FileInputStream(tmpDir));
                byte[] buf = new byte[1024];
                int len = 0;
                while(len != -1) {
                    zos.write(buf, 0, len);
                    len = inputStream.read(buf);
                }

                inputStream.close();
                zos.closeEntry();
            }
        }
        zos.close();
        return res;
    }

    public String byteArray2B64(byte[] bytes) throws IOException {
        return URLEncoder.encode(Base64.getEncoder().encodeToString(bytes), "UTF-8");

    }
}
