
public class AB
{
    public static void ab(int[] x, int[] y, int[] ret)
    {
        int a = 0, b_ = 0;
        boolean[] m = new boolean[10];
        for (int i = 0; i < 4; i++) {
            if (x[i] == y[i]) {
                a++;
            }
            m[x[i]] = true;
        }

        for (int i = 0; i < 4; i++) {
            if (m[y[i]]) {
                b_++;
            }
        }

        ret[0] = a;
        ret[1] = b_ - a;
    }
}
