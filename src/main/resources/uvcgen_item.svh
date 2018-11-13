`ifndef UVCGEN_ITEM__SV
`define UVCGEN_ITEM__SV

class uvcgen_item extends uvm_sequence_item;
    `uvm_object_utils_begin(uvcgen_item)
    `uvm_object_utils_end

    function new (string name="uvcgen_item");
        super.new (name);
    endfunction : new

endclass : uvcgen_item

`endif //!UVCGEN_ITEM__SV