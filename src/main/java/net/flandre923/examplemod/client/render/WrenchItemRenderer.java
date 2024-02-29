package net.flandre923.examplemod.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class WrenchItemRenderer extends BlockEntityWithoutLevelRenderer {
    private static int degree = 0;
    public WrenchItemRenderer(){
        super(null,null);
    }

    @Override
    public void renderByItem(ItemStack pStack, ItemDisplayContext pDisplayContext, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        if(degree==360){
            degree=0;
        }
        degree++;

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        BakedModel bakedModel = itemRenderer.getModel(pStack,null,null,1);
        pPoseStack.pushPose();
        pPoseStack.translate(0.5F, 0.5F, 0.5F);
        float xOffset = -1 / 32f;
        float zOffset = 0;
        pPoseStack.translate(-xOffset, 0, -zOffset);
        pPoseStack.mulPose(Axis.YP.rotationDegrees(degree));
        pPoseStack.translate(xOffset, 0, zOffset);
        itemRenderer.render(pStack, ItemDisplayContext.NONE, false, pPoseStack, pBuffer, pPackedLight, pPackedOverlay, bakedModel);
        pPoseStack.popPose();
    }
}
