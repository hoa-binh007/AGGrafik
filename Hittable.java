interface Hittable {
    boolean hit(Ray r, double t_min, double t_max, Hit_record rec);
}
