`ifndef UTBGEN_VIRTUAL_SEQUENCER__SV
`define UTBGEN_VIRTUAL_SEQUENCER__SV

class utbgen_virtual_sequencer extends uvm_sequencer;
    `uvm_component_utils(utbgen_virtual_sequencer)

    function new (string name, uvm_component parent);
        super.new (name, parent);
    endfunction : new

endclass : utbgen_virtual_sequencer

`endif //!UTBGEN_VIRTUAL_SEQUENCER__SV