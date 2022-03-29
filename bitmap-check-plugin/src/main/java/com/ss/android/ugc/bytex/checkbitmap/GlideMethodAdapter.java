package com.ss.android.ugc.bytex.checkbitmap;


import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

import groovyjarjarasm.asm.Opcodes;

/*
 *  @项目名：  ByteX
 *  @包名：    com.ss.android.ugc.bytex.checkbitmap
 *  @文件名:   GlideMethodAdapter
 *  @创建者:   rookietree
 *  @创建时间:  2022/3/28 15:01
 *  @描述：    TODO
 */
public class GlideMethodAdapter extends AdviceAdapter {

    private MethodVisitor mv;

    protected GlideMethodAdapter(MethodVisitor methodVisitor, int access, String name,
                                 String descriptor) {
        super(Opcodes.ASM5, methodVisitor, access, name, descriptor);
        mv = methodVisitor;
    }


    @Override
    protected void onMethodExit(int opcode) {
        super.onMethodExit(opcode);
        mv.visitVarInsn(ALOAD, 0);
        mv.visitFieldInsn(GETFIELD, "com/bumptech/glide/request/SingleRequest", "requestListeners"
                , "Ljava/util/List;");
        mv.visitMethodInsn(INVOKESTATIC, "com/uxin/aop/GlideHook", "process",
                "(Ljava/util/List;)Ljava/util/List;", false);
    }
}
