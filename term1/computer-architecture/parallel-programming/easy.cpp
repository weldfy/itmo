#include <fstream>
#include <omp.h>
#include <time.h>
#include <stdlib.h>

using namespace std;

int main(int argc, char* argv[]) {

	srand(time(0));

	int thread_count = atoi(string(argv[1]).c_str());
	string input = argv[2];
	string output = argv[3];

	ifstream in(input);
	int r, n;
	in >> r >> n;
	in.close();

	double middle_x = r, middle_y = r;
	thread_count = 12;
	if (thread_count > 0) {
		omp_set_num_threads(thread_count);
	}
	int cnt = 0;
	double start = omp_get_wtime();
#pragma omp parallel for schedule(dynamic, 3000) if (thread_count != -1) 
	for (int i = 0; i < n; i++) {
		double x = (double(rand()%1000) / 1000) * r * 2;
		double y = (double(rand()%1000) / 1000) * r * 2;
		if ((middle_x - x) * (middle_x - x) + (middle_x - y) * (middle_y - y) <= r * r) {
#pragma omp atomic
			cnt++;
		}
	}

	double finish = omp_get_wtime();
	double time = (finish - start) * 1000;
	printf("Time(%i thread(s)) : %g ms\n", thread_count, time);

	ofstream out(output);
	double circle_erea = 4 * r * r * double(cnt) / double(n);
	out << circle_erea << endl;
	out.close();
	return 0;
}