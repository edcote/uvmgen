`ifndef UVCGEN_INTERFACE__SV
`define UVCGEN_INTERFACE__SV

interface uvcgen_if(
    input clock
);

    clocking cb @(posedge clock);
        default input #1step output #0;
    endclocking

endinterface : uvcgen_if

`endif