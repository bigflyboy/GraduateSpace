package com.visionin.wangzhiyuan.vsfacedemo;

import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.zeroo.lib.Zobject;
import com.zeroo.lib.gpu.*;

/**
 * Created by Zero on 2017/2/21.
 */

public interface Iamera {
	///////////////////////////////////////////////////////
	/// 设置处理模式，目前支持：
	/// "": 通路测试
	/// "tuner": 美颜
	/// "facer": 美颜+人脸检测
	public com.zeroo.lib.gpu.Iraphix Mode(String mode);

	///////////////////////////////////////////////////////
	/// 设置预览后才能创建上下文！
	public void Preview(SurfaceHolder surface);
	public void Preview(SurfaceView surface);

	///////////////////////////////////////////////////////
	/// 修改相机输入的朝向
	/// orientation 代表旋转度数（0/-360,+-90,+-180,+-270），负数代表附加镜像翻转
	///	aspect 代表比例控制：0/1/-1 分别代表拉伸填充/截边保持比例（默认）/加边框保持比例
	/// selelction 是机位选择（默认为当前机位）：0/1 代表后置/前置
	public void Orientation(int orientation, int aspect, int selection);
	public void Orientation(int orientation, int aspect);
	public void Orientation(int orientation);

	///////////////////////////////////////////////////////
	/// 打开摄像头，返回实际打开的相机 [宽,高,机位]
	/// w/h 是建议的尺寸（默认保持上次的设置）
	/// selelction 是机位选择（默认保持上次）：0/1 代表后置/前置
	public int[] Open(int w, int h, int selection);
	public int[] Open(int selection);
	public int[] Open(int w, int h);
	public int[] Open();

	///////////////////////////////////////////////////////
	/// 切换摄像头到下一个机位，返回实际打开的相机 [宽,高,机位]
	/// w/h 是建议的尺寸（默认保持上次的设置）
	public int[] Switch(int w, int h);
	public int[] Switch();

	///////////////////////////////////////////////////////
	/// 关闭摄像头
	public void Close();

	///////////////////////////////////////////////////////
	/// 查询是否在激活状态
	public int Active();

	///////////////////////////////////////////////////////
	/// 开启、关闭预览
	public void Display(boolean enable);

	///////////////////////////////////////////////////////
	/// 设置帧预处理器
	public void OnFramer(IameraFramer framer);

	///////////////////////////////////////////////////////
	/// 获取对象
	public Zobject Iamera();
}
