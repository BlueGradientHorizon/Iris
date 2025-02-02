package net.irisshaders.iris.gl.sampler;

import net.irisshaders.iris.gl.GlResource;
import net.irisshaders.iris.gl.IrisRenderSystem;
import org.lwjgl.opengl.GL11C;
import org.lwjgl.opengl.GL13C;
import org.lwjgl.opengl.GL20C;
import org.lwjgl.opengl.GL30C;

public class GlSampler extends GlResource {
	public static final GlSampler MIPPED_LINEAR_HW = new GlSampler(true, true, true, true);
	public static final GlSampler LINEAR_HW = new GlSampler(true, false, true, true);
	public static final GlSampler MIPPED_NEAREST_HW = new GlSampler(false, true, true, true);
	public static final GlSampler NEAREST_HW = new GlSampler(false, false, true, true);
	public static final GlSampler MIPPED_LINEAR = new GlSampler(true, true, false, false);
	public static final GlSampler LINEAR = new GlSampler(true, false, false, false);
	public static final GlSampler MIPPED_NEAREST = new GlSampler(false, true, false, false);
	public static final GlSampler NEAREST = new GlSampler(false, false, false, false);

	public GlSampler(boolean linear, boolean mipmapped, boolean shadow, boolean hardwareShadow) {
		super(IrisRenderSystem.genSampler());

		IrisRenderSystem.samplerParameteri(getId(), GL11C.GL_TEXTURE_MIN_FILTER, linear ? GL11C.GL_LINEAR : GL11C.GL_NEAREST);
		IrisRenderSystem.samplerParameteri(getId(), GL11C.GL_TEXTURE_MAG_FILTER, linear ? GL11C.GL_LINEAR : GL11C.GL_NEAREST);
		IrisRenderSystem.samplerParameteri(getId(), GL11C.GL_TEXTURE_WRAP_S, GL13C.GL_CLAMP_TO_EDGE);
		IrisRenderSystem.samplerParameteri(getId(), GL11C.GL_TEXTURE_WRAP_T, GL13C.GL_CLAMP_TO_EDGE);

		if (mipmapped) {
			IrisRenderSystem.samplerParameteri(getId(), GL11C.GL_TEXTURE_MIN_FILTER, linear ? GL11C.GL_LINEAR_MIPMAP_LINEAR : GL11C.GL_NEAREST_MIPMAP_NEAREST);
		}

		if (hardwareShadow) {
			IrisRenderSystem.samplerParameteri(getId(), GL20C.GL_TEXTURE_COMPARE_MODE, GL30C.GL_COMPARE_REF_TO_TEXTURE);
		}
	}

	@Override
	protected void destroyInternal() {
		IrisRenderSystem.destroySampler(getGlId());
	}

	public int getId() {
		return getGlId();
	}
}
