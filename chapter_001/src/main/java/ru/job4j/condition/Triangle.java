package ru.job4j.condition;
/**
 * Программа вычисляет площадь треугольника
 * при известных координатах трех ее точек.
 *
 */
public class Triangle {
    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    /**
     * Метод вычисления полупериметра по длинам сторон.
     *
     * Формула.
     *
     * (ab + ac + bc) / 2
     *
     * @param ab расстояние между точками a b
     * @param ac расстояние между точками a c
     * @param bc расстояние между точками b c
     * @return Перимент.
     */
    public double period(double ab, double ac, double bc) {
        return (ab + ac + bc) / 2;
    }
    public double max(double first, double second) {
        return (first > second ? first : second);
    }
    /**
     * Метод проверяет можно ли построить треугольник по заданным точкам.
     *
     * Вычисляем длину максимальной стороны maxStorona. Если сумма двух других сторон (далее SumOthers) равна
     * длине максимальной стороны, то все три линии лежат на одной прямой, а значит,
     * все точки лежат на одной прямой. При таком условии треугольник не может существовать.
     * Ниже-вывод формулы аналитически.
     * Сумма двух других сторон : SumOthers=(ab+ac+bc)-maxStorona.
     * Сравниваем SumOthers и maxStorona.
     * SumOthers=maxStorona--->(ab+ac+bc)-maxStorona=maxStorona--->(ab+ac+bc)=2*maxStorona.
     * @param ab Длина от точки a до b.
     * @param ac Длина от точки a до c.
     * @param bc Длина от точки b до c.
     * @return ложь(можно создать)/правда(нельзя создать).
     */
    private boolean exist(double ab, double ac, double bc) {
        double temp = this.max(ab, ac);
        double maxStorona = this.max(temp, bc);
        return (ab + ac + bc) != 2 * maxStorona;
    }
    /**
     * Метод должен вычислить площадь треугольника.
     *
     * @return Вернуть прощадь, если треугольник существует или -1, если треугольника нет.
     */
    public double area() {
        double rsl = -1;
        double ab = this.a.distanceTo(this.b);
        double ac = this.a.distanceTo(this.c);
        double bc = this.b.distanceTo(this.c);
        double p = this.period(ab, ac, bc);
        if (this.exist(ab, ac, bc)) {
            rsl = Math.sqrt(p * (p - ab) * (p - ac) * (p - bc));
        }
        return rsl;
    }
}
