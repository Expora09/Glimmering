package noobanidus.mods.glimmering.init;

import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import noobanidus.mods.glimmering.Glimmering;
import noobanidus.mods.glimmering.particle.BeamParticleType;

public class ModParticles {
  public static final DeferredRegister<ParticleType<?>> particleRegistry = new DeferredRegister<>(ForgeRegistries.PARTICLE_TYPES, Glimmering.MODID);

  public static final RegistryObject<BeamParticleType> BEAM = particleRegistry.register("beam", BeamParticleType::new);

  public static void load() {
  }
}