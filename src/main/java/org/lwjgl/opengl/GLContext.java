package org.lwjgl.opengl;

public class GLContext {

	public static class Capabilities {
		public boolean OpenGL13 = true;
		public boolean OpenGL14 = true;
		public boolean OpenGL15 = true;
		public boolean OpenGL20 = true;
		public boolean GL_ARB_multitexture = true;
		public boolean GL_NV_fog_distance = true;
		public boolean GL_ARB_occlusion_query = true;
	}

	private static final Capabilities capabilities = new Capabilities();

	public static Capabilities getCapabilities() {
		return capabilities;
	}
}
