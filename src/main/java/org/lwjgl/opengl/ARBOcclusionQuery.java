package org.lwjgl.opengl;

import net.lax1dude.eaglercraft.internal.buffer.IntBuffer;

public class ARBOcclusionQuery {

	public static final int GL_SAMPLES_PASSED_ARB = 0x8914;
	public static final int GL_QUERY_COUNTER_BITS_ARB = 0x8864;
	public static final int GL_CURRENT_QUERY_ARB = 0x8865;
	public static final int GL_QUERY_RESULT_ARB = 0x8866;
	public static final int GL_QUERY_RESULT_AVAILABLE_ARB = 0x8867;

	public static void glGenQueriesARB(IntBuffer ids) {
		for (int i = ids.position(); i < ids.limit(); i++) {
			ids.put(i, i + 1);
		}
	}

	public static void glBeginQueryARB(int target, int id) {
	}

	public static void glEndQueryARB(int target) {
	}

	public static void glGetQueryObjectuARB(int id, int pname, IntBuffer result) {
		int pos = result.position();
		if (pname == GL_QUERY_RESULT_AVAILABLE_ARB) {
			result.put(pos, 1);
		} else if (pname == GL_QUERY_RESULT_ARB) {
			result.put(pos, 1);
		}
	}
}
