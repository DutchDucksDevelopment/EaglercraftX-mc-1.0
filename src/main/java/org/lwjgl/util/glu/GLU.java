package org.lwjgl.util.glu;

import net.lax1dude.eaglercraft.internal.buffer.FloatBuffer;
import net.lax1dude.eaglercraft.internal.buffer.IntBuffer;
import net.lax1dude.eaglercraft.opengl.EaglercraftGPU;
import net.lax1dude.eaglercraft.opengl.GlStateManager;

public class GLU {

	public static String gluErrorString(int param) {
		return EaglercraftGPU.gluErrorString(param);
	}

	public static void gluPerspective(float fovy, float aspect, float zNear, float zFar) {
		GlStateManager.gluPerspective(fovy, aspect, zNear, zFar);
	}

	public static boolean gluUnProject(float winX, float winY, float winZ, FloatBuffer modelview, FloatBuffer projection, IntBuffer viewport, FloatBuffer objectCoords) {
		int mvPos = modelview.position();
		int prPos = projection.position();
		int vpPos = viewport.position();
		int ocPos = objectCoords.position();
		float[] mv = new float[16];
		float[] pr = new float[16];
		int[] vp = new int[viewport.remaining()];
		float[] oc = new float[objectCoords.remaining()];
		for (int i = 0; i < 16; i++) {
			mv[i] = modelview.get(mvPos + i);
			pr[i] = projection.get(prPos + i);
		}
		for (int i = 0; i < vp.length; i++) {
			vp[i] = viewport.get(vpPos + i);
		}
		GlStateManager.gluUnProject(winX, winY, winZ, mv, pr, vp, oc);
		for (int i = 0; i < oc.length; i++) {
			objectCoords.put(ocPos + i, oc[i]);
		}
		return true;
	}

}
