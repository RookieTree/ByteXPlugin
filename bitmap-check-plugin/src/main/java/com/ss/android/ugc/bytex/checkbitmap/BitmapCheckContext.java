package com.ss.android.ugc.bytex.checkbitmap;

import com.android.build.gradle.AppExtension;
import com.ss.android.ugc.bytex.common.BaseContext;

import org.gradle.api.Project;

/*
 *  @项目名：  ByteX
 *  @包名：    com.ss.android.ugc.bytex.checkbitmap
 *  @文件名:   BitmapCheckContext
 *  @创建者:   rookietree
 *  @创建时间:  2022/3/25 17:06
 *  @描述：    TODO
 */
public class BitmapCheckContext extends BaseContext<BitmapCheckExtension> {

    public BitmapCheckContext(Project project, AppExtension android,
                              BitmapCheckExtension extension) {
        super(project, android, extension);
    }
}
