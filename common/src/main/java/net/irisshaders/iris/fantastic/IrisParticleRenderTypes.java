package net.irisshaders.iris.fantastic;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureManager;

import static net.minecraft.client.renderer.texture.TextureAtlas.LOCATION_BLOCKS;

public class IrisParticleRenderTypes {

	public static final ParticleRenderType TERRAIN_OPAQUE = new ParticleRenderType("TERRAIN_OPAQUE", RenderType.opaqueParticle(LOCATION_BLOCKS));
}
