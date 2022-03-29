package com.ss.android.ugc.bytex.checkbitmap;

import com.android.build.gradle.AppExtension;
import com.ss.android.ugc.bytex.common.CommonPlugin;
import com.ss.android.ugc.bytex.common.TransformConfiguration;
import com.ss.android.ugc.bytex.common.visitor.ClassVisitorChain;
import com.ss.android.ugc.bytex.pluginconfig.anno.PluginConfig;

import org.gradle.api.Project;

import javax.annotation.Nonnull;

/*
 *  @项目名：  ByteX
 *  @包名：    com.ss.android.ugc.bytex.checkbitmap
 *  @文件名:   BitmapCheckPlugin
 *  @创建者:   rookietree
 *  @创建时间:  2022/3/25 17:04
 *  @描述：    大图检测插件
 */
@PluginConfig("bitmap-check-plugin")
public class BitmapCheckPlugin extends CommonPlugin<BitmapCheckExtension,BitmapCheckContext> {

    @Override
    protected BitmapCheckContext getContext(Project project, AppExtension android,
                                            BitmapCheckExtension extension) {
        return new BitmapCheckContext(project,android,extension);
    }

    @Override
    public boolean transform(@Nonnull String relativePath, @Nonnull ClassVisitorChain chain) {
        //我们需要修改字节码，所以需要注册一个ClassVisitor
        chain.connect(new BitmapCheckClassVisitor(extension));
        return super.transform(relativePath, chain);
    }


    @Nonnull
    @Override
    public TransformConfiguration transformConfiguration() {
        return new TransformConfiguration() {
            @Override
            public boolean isIncremental() {
                //插件默认是增量的，如果插件不支持增量，需要返回false
                //The plugin is incremental by default.It should return false if incremental is not supported by the plugin
                return true;
            }
        };
    }
}
