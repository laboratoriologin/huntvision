//
//  AgendaTableViewCell.swift
//  HuntVision
//
//  Created by Login Informatica on 23/07/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//

import UIKit

class HVLAgendaTableViewCell: UITableViewCell {

    @IBOutlet weak var dataLabel: UILabel!
    
    @IBOutlet weak var descricaoLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    
    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
