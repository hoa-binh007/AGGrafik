interface Material {
    boolean scatter(Ray r_in,Hit_record rec, Vec3 attenuation, Ray scattered);


}
