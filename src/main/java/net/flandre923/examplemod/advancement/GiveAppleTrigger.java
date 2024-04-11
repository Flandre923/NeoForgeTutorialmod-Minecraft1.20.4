package net.flandre923.examplemod.advancement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.flandre923.examplemod.item.ModItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.ExtraCodecs;

import java.util.Optional;

public class GiveAppleTrigger extends SimpleCriterionTrigger<GiveAppleTrigger.Instance> {

    public void trigger(ServerPlayer pPlayer) {
        this.trigger(pPlayer,instance -> true);
    }

    public static Criterion<GiveAppleTrigger.Instance> giveAppleBlock() {
        return InitTrigger.GIVE_RUBY_APPLE.get().createCriterion(new GiveAppleTrigger.Instance(Optional.empty()));
    }

    @Override
    public Codec<Instance> codec() {
        return Instance.CODEC;
    }

    public static class Instance implements SimpleCriterionTrigger.SimpleInstance {
        private Optional<ContextAwarePredicate> player;
        public Instance(Optional<ContextAwarePredicate> player) {
            super();
            this.player = player;
        }

        public static final Codec<GiveAppleTrigger.Instance> CODEC = RecordCodecBuilder.create(
                p_312304_ -> p_312304_.group(
                                ExtraCodecs.strictOptionalField(EntityPredicate.ADVANCEMENT_CODEC, "player").forGetter(GiveAppleTrigger.Instance::player)
                        )
                        .apply(p_312304_, GiveAppleTrigger.Instance::new)
        );

        @Override
        public Optional<ContextAwarePredicate> player() {
            return this.player;
        }
    }
}
