package com.visionin.wangzhiyuan.vsfacedemo;

import com.zeroo.lib.media.Iapturer;

/**
 * Created by Zero on 2017/5/16.
 */

public interface Iraphix {
	///////////////////////////////////////////////////////
	/// 改变默认的输出格式：
	/// media："image"，"video"
	/// fmt："Y"，"I420"，"NV12"，"NV21"，"RGBA"，"BGRA"，"ARGB"，"ABGR"
	public boolean Format(String media, String fmt);

	///////////////////////////////////////////////////////
	/// 初始参数，用于开启前最初设置
	public void Configurate(String key, double value);

	///////////////////////////////////////////////////////
	/// （开启后）改变参数，目前支持：
	/// 美白 "brightening": 0~1
	/// 磨皮 "smoothing": 0~1
	/// 锐化 "sharpening": 0~1
	public void Parameter(String key, double value);

	///////////////////////////////////////////////////////
	/// 开启、关闭、静默预览
	public void Display(boolean enable);
	public void DisplaySilent(int n);

	///////////////////////////////////////////////////////
	/// 设置截屏器
	public Iapturer Snapshoter(int w, int h, int orientation);
	public Iapturer Snapshoter(int w, int h);
	public Iapturer Snapshoter();
	///////////////////////////////////////////////////////
	/// 强制要求截屏下一帧，或连续截屏（视频模式）
	public void Snapshot();
	public void SnapshotStart();
	public void SnapshotStop();

	///////////////////////////////////////////////////////
	/// 设置录屏器
	public Iapturer Capturer(int w, int h, int orientation);
	public Iapturer Capturer(int w, int h);
	public Iapturer Capturer();
	///////////////////////////////////////////////////////
	/// 继续、暂停录屏
	public void CaptureStart();
	public void CaptureStop();

	///////////////////////////////////////////////////////
	/// 开启、关闭人脸特征点标注
	public void FacerStart();
	public void FacerStop();
	public void FacerMarking(boolean on);
	public void FacerMarking3D(int dots);

	///////////////////////////////////////////////////////
	/// 开启、关闭纹理对象（每个纹理可服务一个或多个渲染对象）
	/// fn 为纹理序列帧文件路径，不传递代表外部推送
	public void OpenTexture(String id, String fn);
	public void OpenTexture(String id);
	public void CloseTexture(String id);
	public int TextureFrame(String id, byte[] data, int w, int h, boolean rgba);
	public int TextureFrame(String id, byte[] data, int w, int h);
	public int TextureFrame(String id);

	///////////////////////////////////////////////////////
	/// 开启、关闭渲染对象（必须绑定纹理对象）
	/// idRGBA 为绑定的纹理对象，idA 为绑定的透明度（仅用于外部推送视频类数据）
	/// loop<0（默认）代表纹理序列播放一次后停留在最后一帧，>0 代表重复播放的次数（自动关闭）
	public void OpenObject(String id, String idRGBA, String idA, int loop);
	public void OpenObject(String id, String idRGBA, int loop);
	public void OpenObject(String id, String idRGBA);
	public void CloseObject(String id, String idRGBA, String idA);
	public void CloseObject(String id, String idRGBA);
	///////////////////////////////////////////////////////
	/// 设置 3D 渲染对象归一化的纹理坐标和顶点坐标（缺省三角形顶点索引时为 strip 模式）
	public void ObjectMesh(String id, float[] XY, float[] XYZ, int[] triangles);
	public void ObjectMesh(String id, float[] XY, float[] XYZ);
	///////////////////////////////////////////////////////
	/// 设置渲染对象的纹理图像尺度，scalar 代表约定的单位长度（比如人脸双眼间距）对应的图像尺度
	public void ObjectSize(String id, int width, int height, int scalar);
	///////////////////////////////////////////////////////
	/// 设置渲染对象的锚点，比如："face/0/105“ 代表第0 张人脸第105 号特征点（鼻根）
	public void ObjectAnchor(String id, String anchor);
	public void ObjectAnchor(String id, String anchor, int n);
	///////////////////////////////////////////////////////
	/// 动态修改渲染对象纹理取景框
	public void ObjectWindow(String id, float x, float y, float width, float height);
	///////////////////////////////////////////////////////
	/// 动态修改渲染对象姿态：旋转（以ZYX 为轴），偏移，缩放
	/// 视角坐标系：X 向右，Y 向下，Z 向远
	public void ObjectPose(String id, float[] rotate, float[] offset, float[] scalar);
	public void ObjectRotate(String id, float zn, float yn, float xn);
	public void ObjectRotate(String id, float zn);
	public void ObjectOffset(String id, float ox, float oy, float oz);
	public void ObjectOffset(String id, float ox, float oy);
	public void ObjectScalar(String id, float sx, float sy, float sz);
	public void ObjectScalar(String id, float s);

	///////////////////////////////////////////////////////
	/// 开启、关闭渲染所有对象
	public void RenderStart(boolean mirror);
	public void RenderStart();
	public void RenderStop();
}
