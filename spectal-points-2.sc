
(
var gen = Rake.wavy;
var contrast = 2.pow(10);

var ok, table= [];
Buffer.freeAll;

b = Buffer.allocConsecutive(contrast, Server.default, 256);

contrast.do({|i|
	var p = i/contrast;
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
	sig = sig * Env.linen.ar;
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
t=table;
)
t.size
t[0][99][2]
x.free;

b[0].bufnum