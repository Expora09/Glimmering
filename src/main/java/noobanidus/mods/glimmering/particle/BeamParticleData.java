package noobanidus.mods.glimmering.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import noobanidus.mods.glimmering.init.ModParticles;

import javax.annotation.Nonnull;
import java.util.Locale;

public class BeamParticleData implements IParticleData {
  public final float size;
  public final float r, g, b;
  public final int m;

  public BeamParticleData(float size, float r, float g, float b, int m) {
    this.size = size;
    this.r = r;
    this.g = g;
    this.b = b;
    this.m = m;
  }

  @Nonnull
  @Override
  public ParticleType<BeamParticleData> getType() {
    return ModParticles.BEAM.get();
  }

  @Override
  public void write(PacketBuffer buf) {
    buf.writeFloat(size);
    buf.writeFloat(r);
    buf.writeFloat(g);
    buf.writeFloat(b);
    buf.writeInt(m);
  }

  @Nonnull
  @Override
  public String getParameters() {
    return String.format(Locale.ROOT, "%s %.2f %.2f %.2f %.2f %d", this.getType().getRegistryName(), this.size, this.r, this.g, this.b, this.m);
  }

  public static final IDeserializer<BeamParticleData> DESERIALIZER = new IDeserializer<BeamParticleData>() {
    @Nonnull
    @Override
    public BeamParticleData deserialize(@Nonnull ParticleType<BeamParticleData> type, @Nonnull StringReader reader) throws CommandSyntaxException {
      reader.expect(' ');
      float size = reader.readFloat();
      reader.expect(' ');
      float r = reader.readFloat();
      reader.expect(' ');
      float g = reader.readFloat();
      reader.expect(' ');
      float b = reader.readFloat();
      reader.expect(' ');
      int m = reader.readInt();
      reader.expect(' ');

      return new BeamParticleData(size, r, g, b, m);
    }

    @Override
    public BeamParticleData read(@Nonnull ParticleType<BeamParticleData> type, PacketBuffer buf) {
      return new BeamParticleData(buf.readFloat(), buf.readFloat(), buf.readFloat(), buf.readFloat(), buf.readInt());
    }
  };
}
