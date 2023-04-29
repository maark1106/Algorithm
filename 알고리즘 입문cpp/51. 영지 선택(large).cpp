#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int map[701][701];
int sum[701][701];

int main(void)
{    
    int h, w;
    cin >> h >> w;

    for (int i = 1; i <= h; i++) {
        for (int j = 1; j <= w; j++)
            cin >> map[i][j];
    }

    for (int i = 1; i <= h; i++) 
        for (int j = 1; j <= w; j++) 
            sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + map[i][j];

    int terri_h, terri_w;

    cin >> terri_h >> terri_w;
    
    int max = 0;
    for (int i = terri_h; i <= h; i++) {        
        for (int j = terri_w; j <= w; j++) {
            int terri = sum[i][j] - sum[i - terri_h][j] - sum[i][j - terri_w] + sum[i - terri_h][j - terri_w];
            if (max < terri)
                max = terri;
        }
    }

    cout << max;
    return 0;
}
