package com.visionin.wangzhiyuan.vsfacedemo;

import com.zeroo.lib.gpu.*;

/**
 * Created by Zero on 2017/7/6.
 */

public interface IameraFramer {
	public boolean FramerOpen(com.zeroo.lib.gpu.Iraphix gpu);
	public boolean FramerClose(com.zeroo.lib.gpu.Iraphix gpu);
	public boolean OnFrameEnter(com.zeroo.lib.gpu.Iraphix gpu);
	public boolean OnFrame(com.zeroo.lib.gpu.Iraphix gpu);
	public boolean OnFrameExit(com.zeroo.lib.gpu.Iraphix gpu);
}
