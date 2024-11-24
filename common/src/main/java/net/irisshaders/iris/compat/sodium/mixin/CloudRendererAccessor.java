package net.irisshaders.iris.compat.sodium.mixin;

import net.caffeinemc.mods.sodium.client.render.immediate.CloudRenderer;
import net.minecraft.client.renderer.ShaderProgram;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CloudRenderer.class)
public interface CloudRendererAccessor {
	@Accessor
	static ShaderProgram getCLOUDS_SHADER() {
		throw new IllegalStateException();
	}
}
