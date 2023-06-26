#include<iostream>
using namespace std;

int num[1501];

int main() {

    int n;
    cin >> n;

    int p2, p3, p5, min = 100;
    p2 = 1;
    p3 = 1;
    p5 = 1;

    num[1] = 1;
    for (int i = 2; i <= 1500; i++) {
        if (num[p2] * 2 < num[p3] * 3)
            min = num[p2] * 2;
        else
            min = num[p3] * 3;
        if (num[p5] * 5 < min)
            min = num[p5] * 5;

        if (min == num[p2] * 2)
            p2++;
        if (min == num[p3] * 3)
            p3++;
        if (min == num[p5] * 5)
            p5++;

        num[i] = min;
    }

    cout << num[n];

    return 0;
}
