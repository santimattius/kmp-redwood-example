//
//  CounterViewController.swift
//  iosApp
//
//  Created by Santiago Mattiauda on 6/3/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import UIKit
import Shared
import SwiftUI

class CounterViewController : UIViewController {
    private var delegate: CounterViewControllerDelegate!

    override func viewDidLoad() {
        let redwoodUIView = RedwoodUIView()

        let rootView = redwoodUIView.value
        rootView.translatesAutoresizingMaskIntoConstraints = false

        view.addSubview(rootView)
        rootView.leadingAnchor.constraint(equalTo: view.leadingAnchor).isActive = true
        rootView.topAnchor.constraint(equalTo: view.topAnchor).isActive = true
        rootView.widthAnchor.constraint(equalTo: view.widthAnchor).isActive = true
        rootView.heightAnchor.constraint(equalTo: view.heightAnchor).isActive = true

        self.delegate = CounterViewControllerDelegate(redwoodUIView: redwoodUIView)
    }

    deinit {
        delegate.dispose()
    }
}

struct CounterView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> CounterViewController {
        return CounterViewController()
    }

    func updateUIViewController(_ uiViewController: CounterViewController, context: Context) {
        
    }
}
