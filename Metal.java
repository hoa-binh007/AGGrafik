public class Metal implements Material {
    Vec3 albedo;
    double fuzz;
    public Metal(Vec3 a, double f){
        albedo = a;
        if(f<1){
            fuzz = f;
        }
        else{
            fuzz = 1;
        }

    }
    public boolean scatter(Ray r_in, Hit_record rec, Vec3 attenuation, Ray scattered){
        Vec3 reflected = Lambertian.reflect(Vec3.unit_vector(r_in.direction()), rec.normal);
        scattered.origin = rec.p;
        scattered.direction = reflected.add((Vec3.random_in_unit_sphere().mul(fuzz)));
        // scattered.direction = reflected;
        attenuation.set(albedo);
        return  (Vec3.dot(scattered.direction(), rec.normal))>0;
    }


}
