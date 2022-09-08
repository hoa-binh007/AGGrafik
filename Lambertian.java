public class Lambertian implements Material {
    public Vec3 albedo;

    public Lambertian(Vec3 var1) {
        // Vec3 = color
        this.albedo = var1;
    }

    public boolean scatter(Ray var1, Hit_record var2, Vec3 var3, Ray var4) {
        Vec3 var5 = var2.normal.add(Vec3.random_unit_vector());
        //Catch degenerate scatter direction
        if (var5.near_zero()) {
            var5 = var2.normal;
        }

        var4.origin = var2.p;
        var4.direction = var5;
        var3.set(this.albedo);
        return true;
    }

    //Ray reflection function
    public static Vec3 reflect(Vec3 var0, Vec3 var1) {
        return var0.sub(var1.mul(Vec3.dot(var0, var1) * 2.0D));
    }
}
