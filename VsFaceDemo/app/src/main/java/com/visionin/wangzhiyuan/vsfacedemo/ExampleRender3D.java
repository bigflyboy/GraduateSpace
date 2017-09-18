package com.visionin.wangzhiyuan.vsfacedemo;

import com.zeroo.lib.Zobject;
import com.zeroo.lib.gpu.IameraFramer;
import com.zeroo.lib.gpu.Iraphix;

/**
 * Created by Zero on 2017/7/18.
 */

public class ExampleRender3D implements IameraFramer {
	@Override
	public boolean FramerOpen(Iraphix gpu) {
														/// #1 号纹理（序列帧）
		gpu.OpenTexture("#1", Zobject.DefaultPath()+"mask/mask.png");
		gpu.OpenObject(".1", "#1", -1);                 /// .1 号渲染对象绑定#2 号纹理序列，停留在最后一帧
		gpu.ObjectAnchor(".1", "face/0/102");           /// .1 号渲染对象的锚点：0 号脸第 102 号点：鼻尖
		gpu.ObjectOffset(".1", 0, -.2f, 2.0f);          /// .1 号渲染对象的偏移（单位：两眼间距）
		gpu.ObjectScalar(".1", 5.0f);
		return true;
	}
	@Override
	public boolean FramerClose(Iraphix gpu) {
		gpu.CloseTexture("#1");
		gpu.CloseObject(".1", "#1");
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
