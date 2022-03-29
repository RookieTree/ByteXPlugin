package com.ss.android.ugc.bytex.checkbitmap;

import com.ss.android.ugc.bytex.common.visitor.BaseClassVisitor;

import org.objectweb.asm.MethodVisitor;

/*
 *  @项目名：  ByteX
 *  @包名：    com.ss.android.ugc.bytex.checkbitmap.visitors
 *  @文件名:   BitmapCheckClassVisitor
 *  @创建者:   rookietree
 *  @创建时间:  2022/3/25 17:13
 *  @描述：    TODO
 */
public class BitmapCheckClassVisitor extends BaseClassVisitor {

    private BitmapCheckExtension extension;
    private static final String IMAGELOADER_METHOD_NAME_DESC = "(Ljava/lang/String;Lcom/nostra13/universalimageloader/core/imageaware/ImageAware;" +
            "Lcom/nostra13/universalimageloader/core/DisplayImageOptions;Lcom/nostra13/universalimageloader/core/assist/ImageSize;" +
            "Lcom/nostra13/universalimageloader/core/listener/ImageLoadingListener;" +
            "Lcom/nostra13/universalimageloader/core/listener/ImageLoadingProgressListener;)V";
    /**
     * 当前类名
     */
    private String className;

    public BitmapCheckClassVisitor(BitmapCheckExtension extension) {
        this.extension = extension;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName,
                      String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.className=name;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature
            , String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
        //如果插件开关关闭，则不插入字节码
        if (!extension.isEnable()){
            return mv;
        }
        try {
            //对Glide4.11版本的SingleRequest类的构造方法进行字节码修改
            if(className.equals("com/bumptech/glide/request/SingleRequest") && name.equals("<init>") && descriptor!=null){
                return mv == null ? null : new GlideMethodAdapter(mv,access,name,descriptor);
            }
            //对Fresco的ImageRequest类的构造方法进行字节码修改
           /* if(className.equals("com/facebook/imagepipeline/request/ImageRequest") && name.equals("<init>") && descriptor!=null){
                return mv == null ? null : new FrescoMethodAdapter(mv,access,name,descriptor);
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }
}
