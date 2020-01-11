//This program, given root directory sorts all files into four folders
//documents,images,videos,others

//Happy Sorting


import java.io.File;

public class DocumentSort {
    private static final int DOCUMENT = 0;
    private static final int IMAGE = 1;
    private static final int VIDEO = 2;
    private static final int OTHERS = 3;
    private static File rootDirectory=new File("C:\\Users\\erits\\Downloads");
    public static void main(String[] args) {
        moveFiles(rootDirectory);

    }

    public static void moveFiles(File file) {
        if(file.isFile()){
            File destinationDirectory=null;
            int extensionNumber=findExtensionType(file);
            switch (extensionNumber){
                case DOCUMENT:
                    destinationDirectory=new File(rootDirectory+"\\documents");
                    break;
                case IMAGE:
                    destinationDirectory=new File(rootDirectory+"\\images");
                    break;
                case VIDEO:
                    destinationDirectory=new File(rootDirectory+"\\video");
                    break;
                case OTHERS:
                    destinationDirectory=new File(rootDirectory+"\\others");
                    break;
            }
            if (!destinationDirectory.exists()){
                destinationDirectory.mkdirs();
            }
            file.renameTo(new File(destinationDirectory+"\\"+file.getName()));
        }else if(file.isDirectory()&&file.getName()!="videos"&&
                file.getName()!="images"&&file.getName()!="documents"&&file.getName()!="others"){
            File[] files=file.listFiles();
            for(File iter:files){
                moveFiles(iter);
            }
        }
    }

    public static int findExtensionType(File file){
        String forDoc=".*?[.](pdf|pptx|docx|txt|css|html)";
        String forImage=".*?[.](jpe?g|gif|PNG)";
        String forVideo=".*?[.](mp4|avi|ogg)";
        String fileName=file.getName();
        if(fileName.matches(forDoc)){
            System.out.println("It's a document name: "+fileName);
            return DOCUMENT;
        }else if(fileName.matches(forImage)){
            System.out.println("It's an image name: "+fileName);
            return IMAGE;
        }else if(fileName.matches(forVideo)){
            System.out.println("It's a video name: "+fileName);
            return VIDEO;
        }
        System.out.println("It goes to others name: "+fileName);
        return OTHERS;

    }


}