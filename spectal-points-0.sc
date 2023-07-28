(
var gen = Rake.pulse;
var windowSize = 2.pow(3);

var ok, table= [];
Buffer.freeAll;

b = Buffer.allocConsecutive(windowSize, Server.default, 256);

windowSize.do({|i|
	var p = i/windowSize;
	var fs = gen.value(p);
	table = table.add(fs)
});


ok = table.every({|freqs, i|
	freqs.every({|coll, j|
	if (coll.isCollection, {
		if (coll.size == 3, {
			if (coll.every({|y| y.isNumber }), true , { ["fail a",i].throw });
		}, {["fail b", i].throw});
	}, {["fail c", i].throw;});
	});
});


if (false == ok) {
	"table must be an array of sine3 buffer data.".throw;
};

b.do({|buf, i|
	var fs = table[i];
	buf.sine3(fs[0], fs[1], fs[2]);
});

// Window.closeAll;
FreqScope.new;
SynthDef(\wavetable, {|out, bufstart, bufmax, freq, dur, amp|
	var pos = Line.kr(bufstart, bufmax, dur);
	var sig = VOsc.ar(pos, freq, 0, amp);
	sig = sig * Env.perc.ar;
	Out.ar(out, sig!2);
}).add;

Pbindef(\player,
	\instrument, \wavetable,
	\midinote, Pseq([50, 53, 43] , inf),
	\dur, Prand([1, 2, 3/2], inf),
	\amp, 0.1,
	\bufstart, Pn(b[0].bufnum, inf),
	\bufmax, Pn(b.size),
	\out, 0
).play;
)
