package com.visionin.wangzhiyuan.vsfacedemo;

import com.zeroo.lib.gpu.IameraFramer;
import com.zeroo.lib.gpu.Iraphix;

/**
 * Created by Zero on 2017/7/18.
 */

public class ExampleRender implements IameraFramer {
	protected byte[] tex = null;
	protected int   w = 16,
					h = 8,
					s = 8,
					n = -1;
	@Override
	public boolean FramerOpen(Iraphix gpu) {
		n = 100;
		gpu.OpenTexture("#0");                          /// #0 号纹理（外部推送）
		gpu.OpenObject(".0", "#0", 999);                /// .0 号渲染对象绑定#0 号纹理序列，循环 N 次后自动结束
		gpu.ObjectSize(".0", w, h, s);                  /// .0 号渲染对象的纹理尺度以及“单位尺度”
	///	gpu.ObjectAnchor(".0", "face/0/105");           /// .0 号渲染对象的锚点：0 号脸第 105 号点：鼻根
		gpu.ObjectAnchor(".0", "face/0/105", 9);        /// .0 号渲染对象的锚点：0 号脸第 105 号点：鼻根（第一排点）
		gpu.ObjectAnchor(".0", "face/0/102", 9);        /// .0 号渲染对象的锚点：0 号脸第 102 号点：鼻尖（第二排点）
		gpu.ObjectAnchor(".0", "face/0/101");           /// .0 号渲染对象的锚点：0 号脸第 101 号点：鼻底（其余）
		return true;
	}
	@Override
	public boolean FramerClose(Iraphix gpu) {
		n = -1;
		gpu.CloseTexture("#0");
		gpu.CloseObject(".0", "#0");
		return true;
	}
	@Override
	public boolean OnFrameEnter(Iraphix gpu) {
		int i, j, p;
		if(tex == null) {
			tex = new byte[w*h*4];
		}
		if(n > 0) {
			byte    r = (byte)255,
					g = (byte)0,
					b = (byte)0,
					a = (byte)(n*2);
			for(i = 0; i < h; i ++) {
				p = i*w*4;
				for(j = 0; j < w; j ++, p += 4) {
					tex[p  ] = r;
					tex[p+1] = g;
					tex[p+2] = b;
					tex[p+3] = a;
				}
			}
		}
	///	gpu.ObjectScalar(".0", n*.002f+.1f);
	///	gpu.ObjectRotate(".0", r, r, r);
		return true;
	}
	@Override
	public boolean OnFrame(Iraphix gpu) {
		if(n > 0) {
			if(gpu.TextureFrame("#0", tex, w, h, true) > 0)
				n --;
		}
		else if(n == 0) {
			if(gpu.TextureFrame("#0") > 0)
				n --;
		}
		return true;
	}
	@Override
	public boolean OnFrameExit(Iraphix gpu) {
		return true;
	}
}
