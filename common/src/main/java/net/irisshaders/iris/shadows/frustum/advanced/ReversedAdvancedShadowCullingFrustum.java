package net.irisshaders.iris.shadows.frustum.advanced;

import net.irisshaders.iris.shadows.frustum.BoxCuller;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.joml.FrustumIntersection;
import org.joml.Matrix4fc;
import org.joml.Vector3f;

// nothing is rendered that falls outside the distanceCuller, and everything is rendered that falls within the boxCuller (voxel culler)
public class ReversedAdvancedShadowCullingFrustum extends AdvancedShadowCullingFrustum {
	private final @NotNull BoxCuller distanceCuller;

	public ReversedAdvancedShadowCullingFrustum(Matrix4fc modelViewProjection, Matrix4fc shadowProjection, Vector3f shadowLightVectorFromOrigin, BoxCuller voxelCuller, @NotNull BoxCuller distanceCuller) {
		super(modelViewProjection, shadowProjection, shadowLightVectorFromOrigin, voxelCuller);
		this.distanceCuller = distanceCuller;
	}

	@Override
	public void prepare(double cameraX, double cameraY, double cameraZ) {
		this.distanceCuller.setPosition(cameraX, cameraY, cameraZ);
		super.prepare(cameraX, cameraY, cameraZ);
	}

	@Override
	public boolean isVisible(AABB aabb) {
		if (this.distanceCuller.isCulled(aabb)) {
			return false;
		}

		if (this.boxCuller != null && !this.boxCuller.isCulled(aabb)) {
			return true;
		}

		return this.isVisibleBool(aabb.minX, aabb.minY, aabb.minZ, aabb.maxX, aabb.maxY, aabb.maxZ);
	}

	@Override
	public boolean testAab(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
		if (this.distanceCuller.isCulledSodium(minX, minY, minZ, maxX, maxY, maxZ)) {
			return false;
		}

		if (this.boxCuller != null && !this.boxCuller.isCulledSodium(minX, minY, minZ, maxX, maxY, maxZ)) {
			return true;
		}

		return this.checkCornerVisibilityBool(minX, minY, minZ, maxX, maxY, maxZ);
	}

	@Override
	public int intersectAab(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
		var distanceResult = this.distanceCuller.intersectAab(minX, minY, minZ, maxX, maxY, maxZ);
		if (distanceResult == FrustumIntersection.OUTSIDE) {
			return FrustumIntersection.OUTSIDE;
		}

		if (this.boxCuller != null && !this.boxCuller.isCulledSodium(minX, minY, minZ, maxX, maxY, maxZ)) {
			return FrustumIntersection.INSIDE;
		}

		if (distanceResult == FrustumIntersection.INSIDE) {
			return this.checkCornerVisibility(minX, minY, minZ, maxX, maxY, maxZ);
		}

		var frustumResult = this.checkCornerVisibility(minX, minY, minZ, maxX, maxY, maxZ);
		return frustumResult == FrustumIntersection.INSIDE ? FrustumIntersection.INTERSECT : frustumResult;
	}
}
