prime(2) :- !.
prime(N) :- primes(N), 
            !.
prime(N) :- N > 2, 
            calculatePrimes(N, 2), 
            assert(primes(N)).

calculatePrimes(N, X) :- X > sqrt(N), 
                         !.
calculatePrimes(N, X) :- 0 < mod(N, X),
                         X1 is X + 1,
	                     calculatePrimes(N, X1).

composite(N) :- not prime(N).

sorted([]).
sorted([H]).
sorted([H1, H2 | T]) :- H1 =< H2.

prime_divisors(1, []) :- !.
prime_divisors(1, _, []) :- !.
prime_divisors(N, [H | T]) :- not number(N), 
                               sorted([H | T]),
                               prime(H),
                               prime_divisors(N1, T),
                               N is N1 * H.
prime_divisors(X, [H | T]) :- number(X),
                              is_min(X, H),
	                          K is X / H,
	                          prime_divisors(K, T).

is_min(N, H) :- prime(N),
                H is N,
                !.
is_min(N, H) :- composite(N),
	            check(2, N, H),
                !.

check(I, N, H) :- prime(I),
                  H is I,
                  0 is mod(N, I),check(I, N, H) :- 
                  I1 is I + 1,
                  I1 =< N,
                  check(I1, N, H),
                  !.
                  !.
check(I, N, H) :- not(0 is mod(N, I)),
                  not(prime),
                  I1 is I + 1, 
                  I1 =< N,
                  check(I1, N, H),
                  !.

compact_prime_divisors(N, H) :- prime_divisors(N, H1), compact(H1, H).

compact([], []) :- !.
compact([], [[A, B]]) :- B = 0, !.
compact([H | T], [[A, B] | T1]) :- H = A, B > 0, B1 is B - 1, compact(T, [[A, B1], T1]).
compact([H | T], [[A, B] | T1]) :- not (H = A), B = 0, compact([H | T], T1).

