#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int map[50][50];

int main(void)
{    
    int h, w;
    cin >> h >> w;

    for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++)
            cin >> map[i][j];
    }

    int terri_h, terri_w, res = 0;

    cin >> terri_h >> terri_w;
    
    for (int i = 0; i <= h - terri_h; i++) {        
        for (int j = 0; j <= w - terri_w; j++) {
            int sum = 0;
            for (int k = i; k < i + terri_h; k++) {
                for(int m = j; m< j + terri_w;m++)
                    sum += map[k][m];
            }            
            if (res < sum)
                res = sum;
        }        
    }

    cout << res;
    return 0;
}
