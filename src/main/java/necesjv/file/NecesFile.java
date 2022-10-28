package necesjv.file;
import java.io.*;

import org.apache.commons.io.FileUtils;

import java.nio.file.Files;
import java.util.Arrays;
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
    private final static int BUF_SIZE_1KB = 1024;
    public static final String REGEX_FILE_NAME = "^%s[_][0-9]*[_][0-9]{2}$";
    private void saveFileToDisk(byte[] fileByte, String fileName, String pathFileTemp) throws Exception {
        BufferedInputStream is = null;
        BufferedOutputStream os = null;
        try {
            // Create Path File Temp Directory
            File outputDirectory = new File(pathFileTemp);
            if(!outputDirectory.exists()) {
                outputDirectory.mkdirs();
            }

            is = new BufferedInputStream(new ByteArrayInputStream(fileByte));
            os = new BufferedOutputStream(
                    new FileOutputStream(new File(String.join(File.separator, pathFileTemp, fileName))));
            // Append Byte To File
            byte[] buf = new byte[BUF_SIZE_1KB];

            int ret = -1;
            while((ret = is.read(buf)) > 0) {
                os.write(buf, 0, ret);
                os.flush();
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if(is != null) is.close();
            if(os != null) os.close();
        }
    }

    private void ProcessFileUpload(String fileName, String pathFileTemp, String pathDirectoryUpload) throws Exception {
        // Create file upload
        String pathFileNameUpload = String.join(File.separator,
                pathDirectoryUpload,
                fileName);
        BufferedOutputStream outStream = new BufferedOutputStream(
                new FileOutputStream(pathFileNameUpload));
        try {
            File od = new File(pathFileTemp);
            FileUtil fileUtils = new FileUtil();

            // Get all file temp
            String regexWithFileName = String.format(REGEX_FILE_NAME, fileName);
            String[] fs = od.list(new FilenameFilter(){
                public boolean accept(File dir, String name) {
                    return name.matches(regexWithFileName);
                }
            });

            // Sort file name
            Arrays.sort(fs);

            //Read file temp append to file upload
            for(final String fileNameTemp : fs) {
                // Read file temp to byte
                File fileTemp = new File(String.join(File.separator, pathFileTemp, fileNameTemp));
                byte[] fileContent = Files.readAllBytes(fileTemp.toPath());

                // Append byte file temp to output stream
                fileUtils.join(fileContent, outStream);
            }
        } catch(Exception ex) {
            throw ex;
        }
        finally {
            outStream.close();
        }
    }
}
