package com.visionin.wangzhiyuan.vsfacedemo;

import com.zeroo.lib.Zobject;
import com.zeroo.lib.gpu.IameraFramer;
import com.zeroo.lib.gpu.Iraphix;

/**
 * Created by Zero on 2017/7/18.
 */

public class ExampleRender2D implements IameraFramer {
	@Override
	public boolean FramerOpen(Iraphix gpu) {
														/// #2 号纹理（序列帧）
		gpu.OpenTexture("#2", Zobject.DefaultPath()+"dog_ear/ear.png");
		gpu.OpenObject(".2", "#2", 999);                /// .2 号渲染对象绑定#2 号纹理序列，循环 N 次后自动结束
		gpu.ObjectSize(".2", 240, 120, 50);             /// .2 号渲染对象的纹理尺度以及“单位尺度”（两眼间距）
		gpu.ObjectAnchor(".2", "face/0/105");           /// .2 号渲染对象的锚点：0 号脸第 105 号点：鼻根
		gpu.ObjectOffset(".2", 0, -2.5f, 1.5f);         /// .2 号渲染对象的偏移（单位：两眼间距）
		gpu.ObjectRotate(".2", 0, 0, -15);              /// .2 号渲染对象的三轴（ZYX）旋转
														/// #3 号纹理（序列帧）
		gpu.OpenTexture("#3", Zobject.DefaultPath()+"dog_face/face.png");
		gpu.OpenObject(".3", "#3", 999);                /// .3 号渲染对象绑定#3 号纹理序列，循环 N 次后自动结束
		gpu.ObjectSize(".3", 280, 220, 100);            /// .3 号渲染对象的纹理尺度以及“单位尺度”（两眼间距）
		gpu.ObjectAnchor(".3", "face/0/102");           /// .3 号渲染对象的锚点：0 号脸第 102 号点：鼻尖
		gpu.ObjectOffset(".3", 0, +.3f, 0);             /// .3 号渲染对象的偏移（单位：两眼间距）
		return true;
	}
	@Override
	public boolean FramerClose(Iraphix gpu) {
		gpu.CloseTexture("#2");
		gpu.CloseTexture("#3");
		gpu.CloseObject(".2", "#2");
		gpu.CloseObject(".3", "#3");
		return true;
	}
	@Override
	public boolean OnFrameEnter(Iraphix gpu) {
		return true;
	}
	@Override
	public boolean OnFrame(Iraphix gpu) {
		return true;
	}
	@Override
	public boolean OnFrameExit(Iraphix gpu) {
		return true;
	}
}
