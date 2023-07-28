Rake {
	*pulse {
		var peaks = [ [1, 1], [1/3, 3], [1/3, 5], [1/5, 15]];
		var sway = 1;

		^{|p|
			peaks.collect({|point|
				var weight = point[0];
				var mul = point[1];

				var f = 1;
				var a = 1;
				var r = 0;

				var register = mul.log2.asInt;


				f = mul + sin(p*7pi/8);

				a = weight * a;

				if ((f > 20000) || (f < 0)) {
					a = 0
				};

				[f, a, r];
			});
		}
	}

	*wavy {
		var peaks = [ [1/2, 1], [1/3,3], [1/5,6], [1/2,8],[1/3,20], [1/3, 40], [1/3,12],[1/3,24], [1/2,16], [1/2,32]];

		^{|p|
			peaks.collect({|point|
				var divs = 120;

				var weight = point[0];
				var mul = point[1];

				var f = 1;
				var a = 1;
				var r = 0;

				var register = mul.log2.asInt;
				var rate = (1 + register) % divs;

				a = ((p * rate) % 1);
				f = mul;

				if ((f > 20000) || (f < 0)) {
					a = 0
				};

				[f, a, r];
			});
		}
	}


	*buzzy {
		var peaks = [ [1/7, 1/13], [1/3, 3], [1/3, 5] ];
		var sway = 1;

		^{|p|
			peaks.collect({|point|
				var weight = point[0];
				var mul = point[1];

				var f = 1;
				var a = 1;
				var r = 0;

				var register = mul.log2.asInt;


				f = mul + (mul * cos((3 * 2pi * p)).abs);

				a = weight * a;

				if ((f > 20000) || (f < 0)) {
					a = 0
				};

				[f, a, r];
			});
		}
	}


	*wubstep {
		var peaks = [ [1, 1], [1/3, 3], [1/3, 5], [1/5, 15] ];
		var sway = 1;

		^{|p|
			peaks.collect({|point|
				var weight = point[0];
				var mul = point[1];

				var f = 1;
				var a = 1;
				var r = 0;


				f = mul * (10 * sin(2pi*p).abs);

				a = weight * a;

				if ((f > 20000) || (f < 0)) {
					a = 0
				};

				[f, a, r];
			});
		}
	}

	*sine {
		var peaks = [ [1, 1] ];
		var width = 10;

		^{|p|
			var fs = peaks.collect({|point|
				var weight = point[0];
				var mul = point[1];
				var f = mul
				;
				if ((f > 20000) || (f < 0)) {
					f = 19800;
				};

				[f, 1, 0];
			});

			fs = fs.collect({|data|
				width.collect({|y|
					[data[0]+y, data[1], data[2]];
				});
			});

			fs.flatten;
		}
	}

	*siney {
				var peaks = [ [1/2, 1], [1/3,3], [1/5,6], [1/2,8], [1/2,16], [1/2,32] ];

		^{|p|
			peaks.collect({|point|
				var divs = 12;

				var weight = point[0];
				var mul = point[1];

				var f = 1;
				var a = 1;
				var r = 0;

				var register = mul.log2.asInt;
				var rate = (1 + register) % divs;

				a = weight * ((p * rate) % 1);
				f = mul;

				if ((f > 20000) || (f < 0)) {
					"fail".debug;
					a = 0
				};

				[f, a.debug, r];
			});
		}
	}
}


