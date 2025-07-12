package io.github.jacobwilki01.material.form;

import lombok.Getter;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class MechMaterialBlock extends Block {
    @Getter
    private final String abbreviation;

    public MechMaterialBlock(Properties properties, String abbreviation) {
        super(properties);

        this.abbreviation = abbreviation;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.literal(abbreviation).withColor(0x8b8b8b));

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
